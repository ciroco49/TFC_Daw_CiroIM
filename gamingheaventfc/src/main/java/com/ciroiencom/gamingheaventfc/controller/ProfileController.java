package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.model.ValidationGroups;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getProfile(Model model, @AuthenticationPrincipal User userLogged) {
        Usuario user = usuarioService.findByNicknameWithFavs(userLogged.getUsername());
        
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getCorreo());
        model.addAttribute("description", user.getDescripcion());
        model.addAttribute("juegosFav", user.getJuegosFav());

        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(usuario.getImg()));

        return "pages/profile";
    }

    @GetMapping("/edit")
    public String getProfileEdit(Model model, @AuthenticationPrincipal User userLogged) {
        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());

        if(!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("imgUser", Base64.getEncoder().encodeToString(usuario.getImg()));
        }

        if(model.containsAttribute("errorTypeImg")) {
            model.addAttribute("errorTypeImg", model.getAttribute("errorTypeImg"));
        }

        if(model.containsAttribute("nicknameExists")) {
            model.addAttribute("nicknameExists", model.getAttribute("nicknameExists"));
        }

        return "pages/profile_edit";
    }

    @PostMapping("/edit")
    public String editProfile(@Validated(ValidationGroups.Edit.class) @ModelAttribute("usuario") Usuario user, @AuthenticationPrincipal User userLogged,
                              @RequestParam("uploadImg") MultipartFile uploadImg, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Authentication authentication) {
        long maxSizeBytes = 512 * 1024;

        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuario", bindingResult);
            redirectAttributes.addFlashAttribute("usuario", user);
            return "redirect:/profile/edit";
        }

        if(!uploadImg.isEmpty()
                && (!Arrays.asList("image/png", "image/jpg", "image/jpeg").contains(uploadImg.getContentType())
                || uploadImg.getSize() > maxSizeBytes)) {
            redirectAttributes.addFlashAttribute("errorTypeImg", "The file has to be .png, .jpg or .jpeg and 500KB or less.");
            return "redirect:/profile/edit";
        }

        if(usuarioService.findByNickname(user.getNickname()) != null && !userLogged.getUsername().equals(user.getNickname())) {
            redirectAttributes.addFlashAttribute("nicknameExists", "This nickname is not available.");
            return "redirect:/profile/edit";
        }

        try {
            byte[] imgBytes = (uploadImg.isEmpty()) ? usuario.getImg() : uploadImg.getBytes();

            user.setImg(imgBytes);
            usuarioService.update(userLogged.getUsername(), user);
            updateSession(userLogged, authentication, user.getNickname());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteAccount(@AuthenticationPrincipal User user, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = usuarioService.findByNickname(user.getUsername());
        removeCookieAndActiveSession(request, response);
        usuarioService.delete(usuario);
        return "redirect:/";
    }

    private void removeCookieAndActiveSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void updateSession(@AuthenticationPrincipal User userLogged, Authentication authentication, String newNickname) {
        User newUser = new User(newNickname, "", userLogged.getAuthorities());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(newUser, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

}
