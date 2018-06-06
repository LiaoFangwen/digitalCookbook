package digitalCookBook;
public class RecipeKey {  
    private String recipeName;  
    private String recipeDescription;  
    public RecipeKey(String name, String description) {  
        this.recipeName = name;  
        this.recipeDescription = description;  
    }  
    public int hashCode(){                   
     return this.recipeName.hashCode() * this.recipeDescription.hashCode() ;   
    }      
    public boolean equals(Object obj) {     
       if (this == obj) {                 
              return true;                    
       }           
       if (!(obj instanceof RecipeKey)) {    
             return false;                 
       }      
         
       RecipeKey key = (RecipeKey) obj;    
        
       if (this.recipeName.equals(key.recipeName) && this.recipeName.equals(key.recipeDescription)) {                
           return true ;                    
       } else {             
           return false ;                  
       }         
    }  
}  