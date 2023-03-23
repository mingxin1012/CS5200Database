package melodic.model;

public class TrackVO {
  protected Long albumId;
  protected String albumName;
  protected Long totalTime;

  public TrackVO(Long albumId, String albumName, Long totalTime) {
    this.albumId = albumId;
    this.albumName = albumName;
    this.totalTime = totalTime;
  }

  public Long getAlbumId() {
    return albumId;
  }

  public String getAlbumName() {
    return albumName;
  }

  public Long getTotalTime() {
    return totalTime;
  }

  public void setAlbumId(Long albumId) {
    this.albumId = albumId;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public void setTotalTime(Long totalTime) {
    this.totalTime = totalTime;
  }
}
