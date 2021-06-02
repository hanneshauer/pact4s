package pact4s.munit

import pact4s.{MockProviderServer, ProviderInfoBuilder, VerificationType}

class PactVerifierMUnitSuite extends PactVerifier {
  val mock = new MockProviderServer(2345)

  override val provider: ProviderInfoBuilder = mock.fileSourceProviderInfo(
    consumerName = "Pact4sConsumer",
    providerName = "Pact4sProvider",
    fileName = "./scripts/Pact4sConsumer-Pact4sProvider.json",
    verificationType = VerificationType.RequestResponse
  )

  override val munitFixtures: Seq[Fixture[_]] = Seq(
    ResourceSuiteLocalFixture(
      "Mock Provider Server",
      mock.server
    )
  )

  verifyPacts()
}
