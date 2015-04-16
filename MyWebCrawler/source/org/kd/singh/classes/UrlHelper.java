package org.kd.singh.classes;

public class UrlHelper {
	public static String modifyUrl(String fetched_link, String url){
		String modified_link = "";
		
		if(fetched_link.contains("#")){
//			System.out.println(fetched_link.substring(0, fetched_link.indexOf("#")));
			fetched_link = fetched_link.substring(0, fetched_link.indexOf("#"));
		}
		
		if(fetched_link.substring(0,1).equals(".")){
			fetched_link = fetched_link.substring(1);
		}
		
		if(fetched_link.contains("http://")){
			modified_link = fetched_link;
		}else if(fetched_link.contains("https://")){
			modified_link = fetched_link;
		}else if(fetched_link.contains("//")){
			modified_link = fetched_link.substring(2);
		}else if(fetched_link.contains("mailto:")){
			modified_link = "["+fetched_link+"]";
		}else{
			if(!fetched_link.substring(0,1).equals("/")){
				modified_link = url+"/"+fetched_link;
			}else{
				modified_link = url+fetched_link;
			}
		}
		return modified_link;
	}

}
