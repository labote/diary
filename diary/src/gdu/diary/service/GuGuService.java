package gdu.diary.service;

//서비스 역할 - > 트랜잭션 처리, 로직
public class GuGuService {
	public String getGuGu() {
		StringBuffer sb = new StringBuffer();
		for(int i=2;i<10;i++) {
			for(int j=1;j<10;j++) {
				sb.append(i+" * "+j+" = "+i*j+"\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
