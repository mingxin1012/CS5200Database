package melodic.model;

public class Track {
    


	private int trackId;
    private String trackName;
    private Album album;
    private String artistName;
    private String track_uri;
    private String artist_uri;
    private int duration_ms;
    private String album_name;
    
    public Track(int trackId, String trackName, Album album, String artistName, String track_uri, String artist_uri,
			int duration_ms, String album_name) {
		this.trackId = trackId;
		this.trackName = trackName;
		this.album = album;
		this.artistName = artistName;
		this.track_uri = track_uri;
		this.artist_uri = artist_uri;
		this.duration_ms = duration_ms;
		this.album_name = album_name;
	}

    public Track(int trackId, Album album, String artistName, String track_uri, String artist_uri,
                 int duration_ms, String album_name) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.album = album;
        this.artistName = artistName;
        this.track_uri = track_uri;
        this.artist_uri = artist_uri;
        this.duration_ms = duration_ms;
        this.album_name = album_name;
    }
    

    public Track(String trackName, Album album, String artistName, String track_uri, String artist_uri, int duration_ms, String album_name) {
    	this.trackName = trackName;
        this.album = album;
        this.artistName = artistName;
        this.track_uri = track_uri;
        this.artist_uri = artist_uri;
        this.duration_ms = duration_ms;
        this.album_name = album_name;
    }

    public Track(Album album, String artistName, String track_uri, String artist_uri, int duration_ms, String album_name) {
        this.trackName = trackName;
        this.album = album;
        this.artistName = artistName;
        this.track_uri = track_uri;
        this.artist_uri = artist_uri;
        this.duration_ms = duration_ms;
        this.album_name = album_name;
    }

    public Track(String trackName,int trackId, Album album, String artistName, String track_uri, String artist_uri, int duration_ms) {
    	this.trackName = trackName;
        this.album = album;
        this.artistName = artistName;
        this.track_uri = track_uri;
        this.artist_uri = artist_uri;
        this.duration_ms = duration_ms;
        
    }

    public Track(int trackId, Album album, String artistName, String track_uri, String artist_uri, int duration_ms) {
        this.trackId = trackId;
        this.album = album;
        this.artistName = artistName;
        this.track_uri = track_uri;
        this.artist_uri = artist_uri;
        this.duration_ms = duration_ms;

    }

    public String getTrackName() {
		return trackName;
	}


	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrack_uri() {
        return track_uri;
    }

    public void setTrack_uri(String track_uri) {
        this.track_uri = track_uri;
    }

    public String getArtist_uri() {
        return artist_uri;
    }

    public void setArtist_uri(String artist_uri) {
        this.artist_uri = artist_uri;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
