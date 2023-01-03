package com.example.songvalidation.controller;

import com.example.songvalidation.model.Song;
import com.example.songvalidation.model.SongForm;
import com.example.songvalidation.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;


@Controller
public class SongController {

    @Value("${upload_song}")
    private String fileUpload;

    @Autowired
    private ISongService songService;

    @GetMapping("/")
    public ModelAndView home() {
        Iterable<Song> songs = songService.findAll();
        return new ModelAndView("list","songs", songs);
    }

    @GetMapping("/add")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView ("form");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute SongForm songForm, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "form";
        }
        save(songForm);
        redirectAttributes.addFlashAttribute("message", "Create successfully");
        return "redirect:/";
    }

    private void save(@ModelAttribute SongForm songForm) {
        MultipartFile multipartFile = songForm.getAudio();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Song song = new Song(songForm.getId(), songForm.getName(), songForm.getArtist(), songForm.getCategory(), fileName);
        songService.save(song);
    }
}
