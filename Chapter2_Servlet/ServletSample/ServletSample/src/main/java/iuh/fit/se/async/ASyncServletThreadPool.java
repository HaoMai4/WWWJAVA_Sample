package iuh.fit.se.async;


import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "asyncServletThreadPool", urlPatterns = {"/async-servlet-pool"}, asyncSupported = true)
public class ASyncServletThreadPool extends HttpServlet {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();

        asyncContext.setTimeout(10000);

        CountDownLatch latch = new CountDownLatch(2);
        executor.submit(() -> doTask("Task 1", asyncContext, latch));
        executor.submit(() -> doTask("Task 2", asyncContext, latch));

        executor.submit(() -> {
            try {
                latch.await(); // chờ cả 2 task xong
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println("All tasks completed!");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete(); // chỉ gọi 1 lần duy nhất
            }
        });
    }

    private void doTask(String name, AsyncContext asyncContext, CountDownLatch latch) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000) * 10);
            synchronized (asyncContext) {
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println(name + " completed on " + Thread.currentThread().getName());
                out.flush();
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally {
            latch.countDown(); // báo hiệu task đã xong
        }
    }
}
