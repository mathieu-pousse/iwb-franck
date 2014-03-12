package iwb.bo.coordinates;

public class GeoPoint2D {
	
	private double latitude;
	private double longitude;
	private static final double R = 6372.8; // In kilometers
	
	public GeoPoint2D(){}
	
	public GeoPoint2D(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public GeoPoint2D(String latitude, String longitude){
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double calculateDistance(GeoPoint2D point){
		return calculateDistance(this.latitude, this.longitude, point.latitude, point.longitude);
	}
	
	public double calculateDistance(double lat1, double lon1, double lat2, double lon2){
		
		double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
 
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
        
	}
}
