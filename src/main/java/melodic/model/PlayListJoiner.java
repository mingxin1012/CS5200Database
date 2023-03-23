package melodic.model;

import java.util.Date;

public class PlayListJoiner {
    private int joinerId;

    protected PlayList playList;
    protected Track track;


    public PlayListJoiner(int joinerId, PlayList playList, Track track) {
        this.joinerId = joinerId;
        this.playList = playList;
        this.track = track;
    }

    public PlayListJoiner(PlayList playList, Track track) {
        this.playList = playList;
        this.track = track;
    }

    public int getJoinerId() {
        return joinerId;
    }

    public void setJoinerId(int joinerId) {
        this.joinerId = joinerId;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
