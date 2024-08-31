package email;


import java.sql.Connection;

public class mailapp {
	public static void main(String args[]){
		email emp1=new email("vinay","somala");
		emp1.setAltEmail("jay@gmail.com");
		emp1.setCapacity(5);
		emp1.setPassword("vinay@12");
		emp1.getAltMail();
		emp1.getCapacity();
		emp1.getPassword();
	}
}
