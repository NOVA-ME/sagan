package org.springframework.site.blog.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.site.blog.BlogService;
import org.springframework.site.blog.Post;
import org.springframework.site.blog.PostCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	private BlogService service;

	@Autowired
	public BlogAdminController(BlogService service) {
		this.service = service;
	}

	@RequestMapping(value = "/new", method = { GET, HEAD })
	public String newPost(PostForm postForm, Model model) {
		model.addAttribute("categories", PostCategory.values());
		return "admin/blog/new";
	}

	@RequestMapping(value = "", method = { POST })
	public String createPost(PostForm postForm) {
		Post newPost = service.addPost(postForm);
		return "redirect:/blog/" + newPost.getId() + "-" + newPost.getSlug();
	}

}