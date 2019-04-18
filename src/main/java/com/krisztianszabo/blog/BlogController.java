package com.krisztianszabo.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {
  @Autowired
  private BlogEntryRepository blogEntryRepo;

  @GetMapping("/blog")
  public String blog(Model model) {
    model.addAttribute("entries", blogEntryRepo.getAllBlogEntries());
    return "blog/blog-list-view";
  }

  @GetMapping("/blog/entry/{id}")
  public String detailView(@PathVariable("id")int id, Model model) {
    model.addAttribute("entry", blogEntryRepo.getBlogEntry(id));
    return "blog/blog-entry-view";
  }

  @PostMapping("/blog/edit")
  public String editEntry(HttpServletRequest request, Model model) {
    int entryId = Integer.parseInt(request.getParameter("entryId"));
    model.addAttribute("entry", blogEntryRepo.getBlogEntry(entryId));
    return "blog/blog-entry-edit";
  }
}
