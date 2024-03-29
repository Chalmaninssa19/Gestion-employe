package v.controllers;

import v.authentification.modelClass.User;
import v.authentification.modelClass.Authentificate;
import v.authentification.modelClass.Token;
import v.entities.*;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.Singleton;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelRest;
import eriq.flamework.servlet.ServletEntity;
import eriq.flamework.servlet.SessionEntity;
import generalisation.GenericDAO.GenericDAO;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Singleton
public class AuthentificationController {

	@URLMapping("login.do")
	public ModelRest login(ServletEntity entity){
	 	ModelRest model=new ModelRest();
		try{
           
		}catch(Exception e){
			e.printStackTrace();
		}

        return model;
	}
}
