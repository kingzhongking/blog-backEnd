package cn.celess.blog.controller;

import cn.celess.blog.entity.PartnerSite;
import cn.celess.blog.entity.Response;
import cn.celess.blog.entity.request.LinkApplyReq;
import cn.celess.blog.entity.request.LinkReq;
import cn.celess.blog.service.MailService;
import cn.celess.blog.service.PartnerSiteService;
import cn.celess.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : xiaohai
 * @date : 2019/05/12 13:26
 */
@RestController
public class LinksController {
    @Autowired
    PartnerSiteService partnerSiteService;
    @Autowired
    MailService mailService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/admin/links/create")
    public Response create(@RequestBody LinkReq reqBody) {
        return Response.success(partnerSiteService.create(reqBody));
    }

    @DeleteMapping("/admin/links/del/{id}")
    public Response del(@PathVariable("id") long id) {
        return Response.success(partnerSiteService.del(id));
    }

    @PutMapping("/admin/links/update")
    public Response update(@RequestBody LinkReq reqBody) {
        return Response.success(partnerSiteService.update(reqBody));
    }

    @GetMapping("/links")
    public Response allForOpen() {
        List<PartnerSite> sites = partnerSiteService.findAll().stream().peek(partnerSite -> partnerSite.setOpen(null)).collect(Collectors.toList());
        return Response.success(sites);
    }

    @GetMapping("/admin/links")
    public Response all(@RequestParam("page") int page,
                        @RequestParam("count") int count,
                        @RequestParam(value = "deleted", required = false) Boolean deleted) {
        return Response.success(partnerSiteService.partnerSitePages(page, count, deleted));
    }

    @PostMapping("/apply")
    public Response apply(@RequestBody() LinkApplyReq linkApplyReq) {
        return Response.success(partnerSiteService.apply(linkApplyReq));
    }

    @PostMapping("/reapply")
    public Response reapply(@RequestParam("key") String key) {
        return Response.success(partnerSiteService.reapply(key));
    }
}
