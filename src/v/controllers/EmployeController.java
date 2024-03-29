package v.controllers;

import v.entities.*;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRest;
import eriq.flamework.servlet.ServletEntity;
import eriq.flamework.servlet.SessionEntity;
import generalisation.GenericDAO.GenericDAO;

import java.time.LocalDate;
import java.util.List;

@Controller
@Singleton
public class EmployeController {

	@URLMapping("insert-employe.do")
	public ModelRest save(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Employe employe = new Employe();
			Departement departement = GenericDAO.findById(Departement.class, entity.getData().get("id_departement"), null);
			employe.setDepartement(departement);
			employe.setNom(entity.getData().get("nom"));
			employe.setPrenom(entity.getData().get("prenom"));
			employe.setDtn(LocalDate.parse(entity.getData().get("dtn")));
		
			GenericDAO.save(employe, null);
			 model.addItem("datas", "saved successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("update-employe.do")
	public ModelRest update(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Employe employe = GenericDAO.findById(Employe.class, entity.getData().get("idEmploye"), null);
			Departement departement = GenericDAO.findById(Departement.class, entity.getData().get("id_departement"), null);
			employe.setDepartement(departement);
			employe.setNom(entity.getData().get("nom"));
			employe.setPrenom(entity.getData().get("prenom"));
			employe.setDtn(LocalDate.parse(entity.getData().get("dtn")));
		
			GenericDAO.updateById(employe, entity.getData().get("idEmploye"), null);
			 model.addItem("datas", "deleted successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("delete-employe.do")
	public ModelRest delete(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Employe employe = GenericDAO.findById(Employe.class, entity.getData().get("idEmploye"), null);
			 GenericDAO.deleteById(Employe.class, employe, null);
			 model.addItem("datas", "deleted successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("list-employe.do")
	public ModelRest findAll(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			
			 List<Employe> listEmploye = GenericDAO.getAll(Employe.class, null, null);
			 model.addItem("datas", listEmploye);
			 SessionEntity sessionEntity = new SessionEntity();
			 
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}


}
