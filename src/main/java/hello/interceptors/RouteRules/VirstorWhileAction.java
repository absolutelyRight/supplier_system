package hello.interceptors.RouteRules;

import leap.core.annotation.Bean;
import leap.core.validation.Validation;
import leap.lang.intercepting.State;
import leap.web.action.ActionContext;

@Bean
public class VirstorWhileAction implements RuleAction {
    //白名单，谁都可以浏览的页面
    @Override
    public State Action(ActionContext context, Validation validation) {
        return State.CONTINUE;
    }
}
