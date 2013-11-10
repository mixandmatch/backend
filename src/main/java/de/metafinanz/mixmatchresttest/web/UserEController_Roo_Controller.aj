// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mixmatchresttest.web;

import de.metafinanz.mixmatchresttest.domain.UserE;
import de.metafinanz.mixmatchresttest.web.UserEController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UserEController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UserEController.create(@Valid UserE userE, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userE);
            return "useres/create";
        }
        uiModel.asMap().clear();
        userE.persist();
        return "redirect:/useres/" + encodeUrlPathSegment(userE.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserEController.createForm(Model uiModel) {
        populateEditForm(uiModel, new UserE());
        return "useres/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UserEController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("usere", UserE.findUserE(id));
        uiModel.addAttribute("itemId", id);
        return "useres/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UserEController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("useres", UserE.findUserEEntries(firstResult, sizeNo));
            float nrOfPages = (float) UserE.countUserEs() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("useres", UserE.findAllUserEs());
        }
        return "useres/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UserEController.update(@Valid UserE userE, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userE);
            return "useres/update";
        }
        uiModel.asMap().clear();
        userE.merge();
        return "redirect:/useres/" + encodeUrlPathSegment(userE.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UserEController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, UserE.findUserE(id));
        return "useres/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UserEController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        UserE userE = UserE.findUserE(id);
        userE.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/useres";
    }
    
    void UserEController.populateEditForm(Model uiModel, UserE userE) {
        uiModel.addAttribute("userE", userE);
    }
    
    String UserEController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
