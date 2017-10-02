package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entities.Product;
import manager.ProductManger;
import service.C;

public class ActionProduit {
	
	public static void displayAll(HttpServletRequest request) {
		ArrayList<Product> products = null;
		products = ProductManger.getAll();
		if(products != null){
			request.setAttribute(C.ProductList,products);
			}	
		}
}
