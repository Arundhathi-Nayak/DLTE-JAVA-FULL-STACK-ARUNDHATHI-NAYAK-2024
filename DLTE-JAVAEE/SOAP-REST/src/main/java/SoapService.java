import javax.xml.bind.annotation.XmlRootElement;

public class SoapService {
    @XmlRootElement(name = "GetEmployeeByIdRequest")
    public class GetEmployeeByIdRequest {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @XmlRootElement(name = "GetEmployeeByIdResponse")
    public class GetEmployeeByIdResponse {
        private Employee employee;

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }
    }

    @XmlRootElement(name = "Employee")
    public class Employee {
        private String id;
        private String name;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
