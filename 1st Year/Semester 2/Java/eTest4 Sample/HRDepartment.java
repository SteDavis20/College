package eTest4_2017_2018;

import java.util.ArrayList;
import java.util.Date;
public class HRDepartment implements HRCodes{

	ArrayList<Employee> employeeList = new ArrayList<Employee>();
	public HRDepartment() {

	}

	public void addEmployee(int employeeId, String name, int role) {
		Employee employee = new Employee(employeeId, name, role);
		employeeList.add(employee);
	}

	public void printAllEmployees() {
		for(int i=0; i<employeeList.size(); i++) {
			System.out.println("Employee ID: "+ employeeList.get(i).getEmployeeId() + "\nName: "+ 
					employeeList.get(i).getName()+"\nPromotion Date: "+ employeeList.get(i).getPromotionDate() + 
					"Role: "+ employeeList.get(i).getRole()+"Appraisal History: "+ employeeList.get(i).getAppraisalHistory());
		}
	}

	public Employee findEmployee(int employeeId) {
		for(int i=0; i<employeeList.size(); i++) {
			if(employeeList.get(i).getEmployeeId()==employeeId) {
				return employeeList.get(i);
			}
		}
		return null; 
	}

	public boolean recordEmployeeAppraisal(int employeeId, Date appraisalDate, int score) {
		Employee employee = findEmployee(employeeId);
		if(employee == null) {
			return false;
		}
		EmployeeAppraisal employeeAppraisal = new EmployeeAppraisal(appraisalDate, score);
		ArrayList<EmployeeAppraisal> currentAppraisal = employee.getAppraisalHistory();
		currentAppraisal.add(employeeAppraisal);
		employee.setAppraisalHistory(currentAppraisal);
		return true;
	}

	public boolean promoteEmployee(int employeeId) {
		Employee employee = findEmployee(employeeId);
		int role = employee.getRole();
		if(role != EXECUTIVE) {
			// check the last two appraisal scores in the Employee appraisal history collection
			ArrayList<EmployeeAppraisal> currentAppraisal = employee.getAppraisalHistory();
			int appraisalScore1 = currentAppraisal.get(0).getAppraisalScore();
			int appraisalScore2 = currentAppraisal.get(1).getAppraisalScore();
			// condition b:
			if(appraisalScore1!=EXCEEDED_EXPECTATIONS || appraisalScore2 != EXCEEDED_EXPECTATIONS) {
				return false;
			}
			// condition c:
			// below is getting the date from 1 year ago:
			//		Calendar calendar = Calendar.getInstance();
			//		calendar.setTime(new Date());
			//		calendar.add(Calendar.YEAR,-1);
			//		Date date = calendar.getTime();

			// update role:
			if(role==TESTER) {
				employee.setRole(DEVELOPER);
			}
			else if(role==DEVELOPER) {
				employee.setRole(DESIGNER);
			}
			
			else if(role==DESIGNER) {
				employee.setRole(MANAGER);
			}
			
			else if(role==MANAGER) {
				employee.setRole(EXECUTIVE);
			}
			return true; // for time being
		}
		else {
			return false;
		}
	}
}
