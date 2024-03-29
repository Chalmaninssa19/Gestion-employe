package v.controllers;

import v.entities.Departement;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRest;
import eriq.flamework.servlet.ServletEntity;
import generalisation.GenericDAO.GenericDAO;
import java.util.List;

@Controller
@Singleton
public class DepartementController {

	@URLMapping("insert-departement.do")
	public ModelRest save(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Departement departement = new Departement();
			departement.setNom(entity.getData().get("nom"));
		
			GenericDAO.save(departement, null);
			 model.addItem("datas", "saved successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("update-departement.do")
	public ModelRest update(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Departement departement = GenericDAO.findById(Departement.class, entity.getData().get("idDepartement"), null);
			departement.setNom(entity.getData().get("nom"));
		
			GenericDAO.updateById(departement, entity.getData().get("idDepartement"), null);
			 model.addItem("datas", "deleted successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("delete-departement.do")
	public ModelRest delete(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			Departement departement = GenericDAO.findById(Departement.class, entity.getData().get("idDepartement"), null);
			 GenericDAO.deleteById(Departement.class, departement, null);
			 model.addItem("datas", "deleted successfully");
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}
	@URLMapping("list-departement.do")
	public ModelRest findAll(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
			 List<Departement> listDepartement = GenericDAO.getAll(Departement.class, null, null);
			 model.addItem("datas", listDepartement);
			 return model;
		}catch(Exception e){
			e.printStackTrace();
			model.addItem("error", e.getMessage());
			 return model;
		}
	}


}
