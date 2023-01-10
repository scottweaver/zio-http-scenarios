import zio._
import zio.duration._
import zhttp.http._
import zhttp.service.Server

object HelloWorld extends App {
  val app = Http.collect[Request] { case Method.GET -> !! / "text" =>
    Response.text("Hello World!")

  }

  val effApp = Http.collectZIO[Request] { case Method.GET -> !! / "slow" =>
    for {
      _ <- ZIO
        .debug("Starting slow response...")
      _ <- ZIO.unit
        .delay(5.seconds)
        .tap(_ => ZIO.debug("Finished slow response"))
    } yield Response.text("Sorry I took so long :(")

  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    Server.start(8080, app ++ effApp).exitCode
}
