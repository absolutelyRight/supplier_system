package hello.interceptors;

import hello.interceptors.RouteRules.RuleAction;
import hello.interceptors.RouteRules.VirstorWhileAction;
import leap.core.annotation.Bean;
import leap.core.security.UserPrincipal;
import leap.core.validation.Validation;
import leap.lang.intercepting.State;
import leap.web.action.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Bean
public class Route {
    private VirstorWhileAction virstorWhileRoute ;
    private Logger log;
    private Map<String, RuleAction> distinctRoute;
    private Map<String, RuleAction> vagueRoute;
    public final static String BINGO_LOGIN = "/bingo/login";

    public Route() {
        init();
        //将路由规则加到此处
        distinctRoute.put(BINGO_LOGIN, virstorWhileRoute);
    }

    public void init() {
        virstorWhileRoute = new VirstorWhileAction();
        distinctRoute = new HashMap<String, RuleAction>();
        vagueRoute = new HashMap<String, RuleAction>();
        log = LoggerFactory.getLogger(this.getClass());
    }

    public State Action(String path, ActionContext context, Validation validation) {
        //对输入的路径进行准确匹配，若命中，则转入相应的动作
        RuleAction ruleAction = distinctRoute.get(path);
        if (ruleAction != null) {
            log.info(ruleAction.getClass().getName());
            return ruleAction.Action(context, validation);
        }
        //准确匹配失败后，进行模糊匹配
        for (String v : vagueRoute.keySet()) {
            if (path.matches(v)) {
                ruleAction = vagueRoute.get(vagueRoute);
                log.info(ruleAction.getClass().getName());
                return ruleAction.Action(context, validation);
            }
        }
        Object user=context.getRequest().getServletRequest().getSession().getAttribute("user");
        //未能匹配到模糊匹配的进行默认规则：登陆校验,将未登陆的拦截
        //fixme 还可以优化
//        if (user==null)
//            return State.INTERCEPTED;
        return State.CONTINUE;
    }
}
