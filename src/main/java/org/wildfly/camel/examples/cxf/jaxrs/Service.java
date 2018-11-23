package org.wildfly.camel.examples.cxf.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Path("/srv/")
public class Service {

	private static final Logger LOG = LoggerFactory.getLogger(org.wildfly.camel.examples.cxf.jaxrs.Service.class);

	@POST
	@Path("/delay")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delay(Request req) throws InterruptedException {
		Thread.sleep(req.getDelay());
		return Response.ok(UuidBean.uuid() + " " + req.getText(), MediaType.TEXT_PLAIN_TYPE).build();
	}

	@GET
	public Response list() {
		return Response.ok(UuidBean.uuid() + " NOP", MediaType.TEXT_PLAIN_TYPE).build();
	}

	@GET
	@Path("/count")
	public Response count(@QueryParam("count") long count, @QueryParam("delay") @DefaultValue("0") long delay, @QueryParam("cid") @DefaultValue("null") String cid) throws InterruptedException {
		long c = count + 1;

		LOG.info("count before delay {} {}", cid, c);
		Thread.sleep(delay);
		LOG.info("count after delay {} {}", cid, c);

		return Response.ok(UuidBean.uuid() + " " + c, MediaType.TEXT_PLAIN_TYPE).build();
	}

	private int doCPUIntensiveCalculation(final Timer timer) {
		final int listSize = 1000;
		final Random rnd = new Random();
		int sum = 0;
		while (!timer.elapsed()) {
			final long start = System.currentTimeMillis();

			int[] numbers = new int[listSize];
			for (int i = 0; i < listSize; ++i) {
				numbers[i] = rnd.nextInt();
			}

			Arrays.sort(numbers);
			sum += numbers[0];
//			System.out.printf("Time taken [ms] %s%n", System.currentTimeMillis() - start);
		}
		return sum;
	}

	@GET
	@Path("/cpuload")
	public Response cpuload(@QueryParam("time") long time, @QueryParam("pause") @DefaultValue("0") long pause) throws InterruptedException {

		String output = "OK: " + doCPUIntensiveCalculation(new Timer(time, TimeUnit.MILLISECONDS));

		TimeUnit.MILLISECONDS.sleep(pause);

		return Response.ok(output).build();
	}
}