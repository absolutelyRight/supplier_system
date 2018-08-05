package hello.interceptors;

import leap.core.annotation.Bean;
import leap.core.annotation.Inject;
import leap.core.validation.Validation;
import leap.lang.intercepting.State;
import leap.web.action.ActionContext;
import leap.web.action.ActionExecution;
import leap.web.action.ActionInterceptor;

@Bean
public class MyActionInterceptor implements ActionInterceptor {
    @Inject
    private Route route;
    @Override
    public State preExecuteAction(ActionContext context, Validation validation) throws Throwable {
        System.out.println("MyActionInterceptor.preExecuteAction");
        return route.Action(context.getPath(),context,validation);
    }

    @Override
    public State postExecuteAction(ActionContext context, Validation validation, ActionExecution execution) throws Throwable {
        System.out.println("MyActionInterceptor.postExecuteAction");
        return State.CONTINUE;
    }

    @Override
    public State onActionFailure(ActionContext context, Validation validation, ActionExecution execution) throws Throwable {
        System.out.println("MyActionInterceptor.onActionFailure");
        return State.CONTINUE;
    }

    @Override
    public void completeExecuteAction(ActionContext context, Validation validation, ActionExecution execution) throws Throwable {
        System.out.println("MyActionInterceptor.completeExecuteAction");
    }
}