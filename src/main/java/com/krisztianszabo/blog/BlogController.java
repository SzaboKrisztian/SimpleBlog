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

  @PostMapping("/blog/save")
  public String saveEntry(@ModelAttribute BlogEntry entry) {
    blogEntryRepo.editBlogEntry(entry);
    return "redirect:/blog/entry/" + entry.getId();
  }

  @PostMapping("/blog/delete")
  public String deleteEntry(HttpServletRequest request, Model model) {
    int entryId = Integer.parseInt(request.getParameter("entryId"));
    model.addAttribute("entry", blogEntryRepo.getBlogEntry(entryId));
    return "blog/blog-entry-delete";
  }

  @PostMapping("/blog/delete/confirm")
  public String deleteConfirmed(@ModelAttribute BlogEntry entry) {
    blogEntryRepo.deleteBlogEntry(entry.getId());
    return "redirect:/blog";
  }
}
