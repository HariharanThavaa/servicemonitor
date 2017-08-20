/**
 * Class to Estabish the connection with existing website
 */
package com.yukon.dao;

import com.yukon.vo.Service;

/**
 * @author Thavaa Hariharan
 *
 */
public interface ServiceDao {
	
	//Try establishing TCP connection with Host and Port
	public void update(Service service);

}
