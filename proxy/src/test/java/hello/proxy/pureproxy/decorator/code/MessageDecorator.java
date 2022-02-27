package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(final Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        final String operation = component.operation();
        final String decorationResult = "🔥" + operation + "🔥";
        log.info("MessageDecorator 적용전 = {}, 적용 후 = {}", operation, decorationResult);
        return decorationResult;
    }

}
