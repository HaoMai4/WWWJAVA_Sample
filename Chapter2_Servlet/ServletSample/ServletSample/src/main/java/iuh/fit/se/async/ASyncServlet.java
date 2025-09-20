package iuh.fit.se.async;


import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "asyncServlet", urlPatterns = {"/async-servlet"}, asyncSupported = true)
public class ASyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();

        asyncContext.setTimeout(10000);

        asyncContext.addListener(new MyAsyncListener());

        // Thread 1
        asyncContext.start(() -> {
            long start = System.nanoTime();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 100) * 100);
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println(Thread.currentThread().getName() + " - Task completed in " + (System.nanoTime() - start) + " ns");
                out.flush();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }


            System.out.println("Time to completed long task  " + (System.nanoTime() - start));
        });

        // Thread 2
        asyncContext.start(() -> {
            long start = System.nanoTime();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 100) * 100);
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println(Thread.currentThread().getName() + " - Task completed in " + (System.nanoTime() - start) + " ns");
                out.flush();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                asyncContext.complete();
            }
        });
    }
}
