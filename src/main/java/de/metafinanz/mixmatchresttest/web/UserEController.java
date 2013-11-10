package de.metafinanz.mixmatchresttest.web;
import de.metafinanz.mixmatchresttest.domain.UserE;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/useres")
@Controller
@RooWebScaffold(path = "useres", formBackingObject = UserE.class)
@RooWebJson(jsonObject = UserE.class)
@RooWebFinder
public class UserEController {
}
