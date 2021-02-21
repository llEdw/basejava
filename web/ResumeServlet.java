import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.ResumeTestData.fillResume;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = new ListStorage();
    private static final String UUID1 = UUID.randomUUID().toString();
    private static final String UUID2 = UUID.randomUUID().toString();
    private static final String UUID3 = UUID.randomUUID().toString();
    private static final String UUID0 = UUID.randomUUID().toString();
    private static final String UUID4 = UUID.randomUUID().toString();

    // private static final Resume R1 = fillResume(UUID1, "c");
    private static final Resume R1 = new Resume(UUID1, "c");
    private static final Resume R2 = fillResume(UUID2, "a");
    private static final Resume R3 = fillResume(UUID3, "b");
    private static final Resume R0 = fillResume(UUID0, "e");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write("<html>" +
                "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">" +
                "<tr>" +
                "<th>uuid</th>" +
                "<th>fullname</th>" +
                "</tr>");
        List<Resume> list = storage.getAllSorted();
        for (Resume r : list) {
            pw.write("<tr>" +
                    "<td>" + r.getUuid() + "</td>" +
                    "<td>" + r.getFullName() + "</td>" +
                    "</tr>");
        }
        pw.write("</table>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
