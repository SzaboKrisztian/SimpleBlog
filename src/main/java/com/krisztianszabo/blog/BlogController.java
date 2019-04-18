package com.krisztianszabo.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
