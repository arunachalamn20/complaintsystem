package com.example.complaintsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class service {

    @Autowired
    repository rep;

    @Autowired
    Emailservice emailservice;

    //create
    public Complaint create(Complaint complaint){

        complaint.setStatus("Pending");
        complaint.setCreatedat(LocalDateTime.now());
        return rep.save(complaint);
    }

    //getbyid
    public Complaint getbyid(Long id){

        Complaint complaint = rep.findById(id).orElse(null);
        if(complaint!=null){
            return complaint;
        }
        return null;
    }

    //getall
    public List<Complaint> getall(){

        return rep.findAll();
    }

    //update
    public Complaint update(Long id, Complaint complaint){

        Complaint existingcomplaint = rep.findById(id).orElse(null);
        if(existingcomplaint!=null){

            existingcomplaint.setUsername(complaint.getUsername());
            existingcomplaint.setDepartment(complaint.getDepartment());
            existingcomplaint.setIssue(complaint.getIssue());
            existingcomplaint.setStatus(complaint.getStatus());
            existingcomplaint.setPriority(complaint.getPriority());

            return rep.save(existingcomplaint);
        }
        return null;
    }

    //deletebyid
    public void deletebyid(Long id){

        rep.deleteById(id);

    }

    //deleteall
    public void deleteall(){

        rep.deleteAll();
    }

    //getbystatus
    public List<Complaint> getbystatus(String status){

        return rep.findByStatus(status);
    }

    //getbypriority
    public List<Complaint> getbypriority(String priority){

        return rep.findByPriority(priority);
    }

    //getbydepartment
    public List<Complaint> getbydepartment(String department){

        return rep.findByDepartment(department);
    }

    //getbyusername
    public List<Complaint> getbyusername(String username){

        return rep.findByUsername(username);
    }

    //update status only
    public Complaint updatestatus(Long id,String status){

        Complaint complaint = rep.findById(id).orElse(null);

        if(complaint!=null){
            complaint.setStatus(status);
            if(status.equalsIgnoreCase("Resolved")){
                emailservice.sendmail(complaint.getEmail(),"Issue has been Resolved","Dear " + complaint.getUsername() + ",\n\n" +
                        "Your complaint has been resolved successfully.\n\n" +
                        "Thank you for using our Complaint Management System.\n\n" +
                        "Best Regards,\n" +
                        "Service Team");
            }
            return rep.save(complaint);
        }
        return null;
    }

    //count by status
    public Long countbystatus(String status){

        return rep.countByStatus(status);
    }

    //count by priority
    public Long countbypriority(String priority){

        return rep.countByPriority(priority);
    }

    //get all new complaint
    public List<Complaint> latestcomplaint(){

        return rep.findAllByOrderByCreatedatDesc();
    }

    //total complaint count
    public Long totalcomplaint(){

        return rep.count();
    }

    //departmentwise complaint count
    public Long departmentwisecount(String department){

        return rep.countByDepartment(department);
    }

    //get all details in dashboard
    public Dashboardresponse getalldetails(){

        Dashboardresponse d = new Dashboardresponse();
        d.setTotalcomplaint(rep.count());
        d.setPendingcomplaints(rep.countByStatus("Pending"));
        d.setInprogresscomplaints(rep.countByStatus("In Progress"));
        d.setResolvedcomplaints(rep.countByStatus("Resolved"));
        d.setHighprioritycomplaints(rep.countByPriority("High"));

        return d;

    }


}
