package setInject;

import org.springframework.stereotype.Component;

@Component
public class InstB {
	public InstB() {
		System.out.println("InstB 的构造方法.....");
	}
}
