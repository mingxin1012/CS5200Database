package melodic.servlet;

import melodic.dal.PlayListDao;
import melodic.dal.TrackDao;
import melodic.dal.AlbumDao;
import melodic.dal.PlayListJoinerDao;
import melodic.model.PlayList;
import melodic.model.PlayListJoiner;
import melodic.model.Track;
import melodic.model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userPlayList")
public class UserPlaylist extends HttpServlet {

    protected PlayListDao playListDao;

    @Override
    public void init() throws ServletException {
        playListDao = PlayListDao.getInstance();
    }
    // get all tracks from playlist
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate UserName.
        String playListId = req.getParameter("playListId");
        if (playListId == null || playListId.trim().isEmpty()) {
            messages.put("title", "Invalid playListId.");
        } else {
            messages.put("title", "PlayList for " + playListId);
        }

        // Retrieve BlogUsers, and store in the request.
        List<Track> tracks;
        try {
            PlayList playlist = new PlayList(Long.valueOf(playListId));
            tracks = PlayListDao.getInstance().getTracksForPLayList(playlist);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("tracks", tracks);
        req.getRequestDispatcher("/UserPlaylist.jsp").forward(req, resp);
    }

    // add track to playlist
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Retrieve and validate UserName.
        String playListId = req.getParameter("playListId");
        if (playListId == null || playListId.trim().isEmpty()) {
            messages.put("title", "Invalid playListId.");
        }
        // Retrieve and validate name.
        String trackURL = req.getParameter("trackURL");
        String albumName = req.getParameter("albumName");
        String artistName = req.getParameter("artistName");
        String artistURL = req.getParameter("artistURL");
        String durationMS = req.getParameter("durationMS");

        if (albumName == null || durationMS == null || artistName == null || trackURL == null || artistURL == null) {
            messages.put("success", "Invalid inputs");
        } else {
            try {
                // create album if album name is not in db else return stored album
                Album album = AlbumDao.getInstance().getAlbumByName(albumName);
                if(album == null) {
                    album = AlbumDao.getInstance().create(new Album(albumName));
                }
                // create track if track is not in db else return stored track
                Track t = TrackDao.getInstance().getTrackFromTrackURL(trackURL);
                if(t == null){
                    t = TrackDao.getInstance().create(new Track(album, artistName, trackURL, artistURL, Integer.valueOf(durationMS), albumName));
                }
                // add track and playlist id into playlist joiner table
                PlayList pl = PlayListDao.getInstance().getPlayListById(Integer.valueOf(playListId));
                PlayListJoinerDao.getInstance().create(new PlayListJoiner(pl, t));
                messages.put("success", "Successfully added track to playlist");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        // will refresh page
        req.getRequestDispatcher("/UserPlaylist.jsp").forward(req, resp);
    }


}
