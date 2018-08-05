package hello.interceptors.RouteRules;
import leap.core.validation.Validation;
import leap.lang.intercepting.State;
import leap.web.action.ActionContext;

public interface RuleAction {

    public State Action(ActionContext context, Validation validation);
}
