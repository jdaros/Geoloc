package models;

/**
 * Class IpInformation
 * details : Simple object to store the address information of an IP device
 * @author : JDS (TD)
 * @version : 1.0 - Initial version
 */
public class IpInformation {

	public String latitude;
	public String longitude;
	public String city;
	public String region;
	public String country;
	
	/**
	 * Constructor
	 * 
	 * @param  latitude - the latitude
	 * @param  longitude - the longitude
	 * @param  city - the latitude
	 * @param  region - the region
	 * @param  latitude - the country
	 * 
	 */
	public IpInformation(String latitude, String longitude, String city,
			String region, String country) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.region = region;
		this.country = country;
	}

}
