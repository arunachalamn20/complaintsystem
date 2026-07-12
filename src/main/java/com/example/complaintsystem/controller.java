package com.example.complaintsystem;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@Tag(name = "Complaint Management",description = "APIs for complaint management system")
@RestController
@RequestMapping("/complaint")
public class controller {

    @Autowired
    service ser;
    //create
    @Operation(summary = "Create a new complaint")
    @PostMapping("/create")
    public ResponseEntity<Complaint> create(@Valid @RequestBody Complaint complaint){

        return new ResponseEntity<>(ser.create(complaint), HttpStatus.CREATED);
    }

    //getbyid
    @Operation(summary = "Get complaint by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<Complaint> getbyid(@PathVariable Long id){

        Complaint c = ser.getbyid(id);
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //getall
    @Operation(summary = "Get all complaints")
    @GetMapping("/get")
    public ResponseEntity<List<Complaint>> getall(){

        return new ResponseEntity<>(ser.getall(),HttpStatus.OK);
    }

    //update
    @Operation(summary = "Update complaint by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Complaint> update(@PathVariable Long id, @Valid @RequestBody Complaint complaint){

        Complaint c = ser.update(id,complaint);
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //deletebyid
    @Operation(summary = "Delete complaint by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable Long id){

        Complaint c = ser.getbyid(id);
        if(c==null){
            return new ResponseEntity<>("Complaint not found",HttpStatus.NOT_FOUND);
        }
        ser.deletebyid(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);


    }

    //deleteall
    @Operation(summary = "Delete all complaints")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteall(){

        ser.deleteall();
        return new ResponseEntity<>("All complaints deleted successfully",HttpStatus.OK);
    }

    //getbystatus
    @Operation(summary = "Get complaint by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Complaint>> getbystatus(@PathVariable String status){

        if(!(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Resolved"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Complaint> c = ser.getbystatus(status);
        if(c.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c,HttpStatus.OK);

    }

    //getbypriority
    @Operation(summary = "Get complaint by priority")
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Complaint>> getbypriority(@PathVariable String priority){

        if(!(priority.equalsIgnoreCase("High") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("Low"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Complaint> c = ser.getbypriority(priority);
        if(c.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //getbydepartment
    @Operation(summary = "Get complaint by department")
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Complaint>> getbydepartment(@PathVariable String department){

        if(!(department.equalsIgnoreCase("IT") || department.equalsIgnoreCase("HR") || department.equalsIgnoreCase("Finance") || department.equalsIgnoreCase("Admin"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Complaint> c = ser.getbydepartment(department);
        if(c.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //getbyusername
    @Operation(summary = "Get complaint by username")
    @GetMapping("/username/{username}")
    public ResponseEntity<List<Complaint>> getbyusername(@PathVariable String username){

        List<Complaint> c = ser.getbyusername(username);
        if(c.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //update status only
    @Operation(summary = "Update only status")
    @PutMapping("/status/{id}")
    public ResponseEntity<Complaint> updatestatus(@PathVariable Long id, @RequestBody Complaint complaint){

        if(!((complaint.getStatus()).equalsIgnoreCase("Pending") || (complaint.getStatus()).equalsIgnoreCase("In Progress") || (complaint.getStatus()).equalsIgnoreCase("Resolved"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Complaint c = ser.updatestatus(id,complaint.getStatus());
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //count by status
    @Operation(summary = "Count complaints by status")
    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countbystatus(@PathVariable String status){

        if(!(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Resolved"))){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ser.countbystatus(status),HttpStatus.OK);
    }

    //count by priority
    @Operation(summary = "Count complaints by priority")
    @GetMapping("/count/priority/{priority}")
    public ResponseEntity<Long> countbypriotiy(@PathVariable String priority){

        if(!(priority.equalsIgnoreCase("High") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("Low"))){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ser.countbypriority(priority),HttpStatus.OK);
    }

    //get all new complaints
    @Operation(summary = "Get all new complaints")
    @GetMapping("/latest")
    public ResponseEntity<List<Complaint>> latestcomplaint(){

        return new ResponseEntity<>(ser.latestcomplaint(),HttpStatus.OK);
    }

    //total complaint count
    @Operation(summary = "Total complaints count")
    @GetMapping("/count")
    public ResponseEntity<Long> totalcomplaint(){

        return new ResponseEntity<>(ser.totalcomplaint(),HttpStatus.OK);
    }

    //departmentwise complaint count
    @Operation(summary = "Department wise complaint count")
    @GetMapping("/count/department/{department}")
    public ResponseEntity<Long> departmentwisecount(@PathVariable String department){

        if(!(department.equalsIgnoreCase("IT") || department.equalsIgnoreCase("HR") || department.equalsIgnoreCase("Finance") || department.equalsIgnoreCase("Admin"))){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ser.departmentwisecount(department),HttpStatus.OK);
    }

    //get all details in dashboard
    @Operation(summary = "Get all details in dashboard")
    @GetMapping("/dashboard")
    public ResponseEntity<Dashboardresponse> getalldetails(){

        return new ResponseEntity<>(ser.getalldetails(),HttpStatus.OK);

    }


}
