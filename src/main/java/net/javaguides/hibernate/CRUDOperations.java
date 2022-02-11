package net.javaguides.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import net.javaguides.hibernate.entity.Student;
import net.javaguides.hibernate.util.JPAUtil;

/**
 *  JPA CRUD Operations
 * @author Ramesh Fadatare
 *
 */
public class CRUDOperations {
    public void insertEntity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name of student:");
		String firstName = sc.nextLine();
		System.out.println("Enter last name of student:");
		String lastName = sc.nextLine();
		System.out.println("Enter email of student:");
		String email = sc.nextLine();
		
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = new Student(firstName, lastName, email);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void findEntity() {
		System.out.println("Please enter in the Id of the Student you are wanting to find");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		try {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        entityManager.getTransaction().begin();
	        
	        Student student = entityManager.find(Student.class, id);
	        System.out.println("student id :: " + student.getId());
	        System.out.println("student firstname :: " + student.getFirstName());
	        System.out.println("student lastname :: " + student.getLastName());
	        System.out.println("student email :: " + student.getEmail());
	        entityManager.getTransaction().commit();
	        entityManager.close();
		}catch(NullPointerException e) {
			System.out.println("ID not found!");
		}
    }
    
    public void findEntityAll() {
		
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = entityManager.createQuery("select s from student s");
			List<Student> allStudent = new ArrayList<Student>();
			allStudent = q.getResultList();
			allStudent.forEach(p -> System.out.println(p));
		}catch (NumberFormatException e) {
			System.out.println("\nDone displaying list or it is empty or it is missing index 1\n");
		}
		entityManager.close();

	}
    
	public void findEmail() {
		System.out.println("Please enter in the Id of the Student you are wanting update the email for:");
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		try {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			Student student = entityManager.find(Student.class, a);
			System.out.println("Email of Student with Id " + a + ": " + student.getEmail());
		}catch (NullPointerException e) {
    		System.out.println("ID not found!");
    	}

	}

    public void updateEntity() {
    	System.out.println("Enter the Id of the Student you are wanting to change the name for:");
		Scanner scanner = new Scanner(System.in);
		int id = scanner.nextInt();
		try {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        entityManager.getTransaction().begin();

	        Student student = entityManager.find(Student.class, id);
	        System.out.println("student id :: " + student.getId());
	        System.out.println("student firstname :: " + student.getFirstName());
	        System.out.println("student lastname :: " + student.getLastName());
	        System.out.println("student email :: " + student.getEmail());

	        // The entity object is physically updated in the database when the transaction
	        // is committed
	        student.setFirstName("Ram");
	        student.setLastName("jadhav");
	        entityManager.getTransaction().commit();
	        entityManager.close();
		}catch (NullPointerException e) {
    		System.out.println("ID not found!");
    	}
        
    }

    public void removeEntity() {
    	System.out.println("Enter id of student to remove");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
    	try {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            Student student = entityManager.find(Student.class, id);
            System.out.println("student id :: " + student.getId());
            System.out.println("student firstname :: " + student.getFirstName());
            System.out.println("student lastname :: " + student.getLastName());
            System.out.println("student email :: " + student.getEmail());
            entityManager.remove(student);
            entityManager.getTransaction().commit();
            entityManager.close();
    	}catch (NullPointerException e) {
    		System.out.println("ID not found!");
    	}

    }
}