package model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_property_mapping")
public class PropertyMapping {

	@Id
	int mapping_id;
	int client_id;
	int property_id;
	String property_value;
	String version_id;
	int modified_by;
	int environment_id;
	String is_approved;
	int approved_by;	
	String reject_comment;
	
	
	public int getMapping_id() {
		return mapping_id;
	}
	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
	public String getProperty_value() {
		return property_value;
	}
	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}
	public String getVersion_id() {
		return version_id;
	}
	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public int getEnvironment_id() {
		return environment_id;
	}
	public void setEnvironment_id(int environment_id) {
		this.environment_id = environment_id;
	}
	public String isIs_approved() {
		return is_approved;
	}
	public void setIs_approved(String is_approved) {
		this.is_approved = is_approved;
	}
	public int getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(int approved_by) {
		this.approved_by = approved_by;
	}
	public String isReject_comment() {
		return reject_comment;
	}
	public void setReject_comment(String reject_comment) {
		this.reject_comment = reject_comment;
	}
	
	
	public String toString(){
		return  mapping_id+" "+client_id+" "+property_id+" "+property_value+" "+version_id+" "+modified_by+" "+environment_id+" "+is_approved+" "+approved_by+" "+reject_comment;
	}
	
	
	
}
