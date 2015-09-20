package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.NamedEvent;

@ManagedBean
public class TestBean {

	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	public String getUserText() {
		return utilBean.getUtilText();
	}

	public UtilBean getUtilBean() {
		return utilBean;
	}

	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

}
