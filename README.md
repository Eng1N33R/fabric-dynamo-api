# Dynamo API

Dynamo API is an opinionated block connectivity library. It enables transmitting generic payloads from `Supplier` to `Receiver` block entities.

Its main principles are:

* Connections are peer-to-peer, without complex hub-endpoint networks.
* Payload transmission is _push-based_ - this means that unlike how electrical devices _draw_ power, the devices in Dynamo API _supply_ power which is potentially consumed.
* Connector blocks do _not_ have associated block entities for better performance.
* Payload transmission is instant _across connectors_.

This API is used in [Mechanical Tech](https://github.com/Eng1N33R/MechanicalTech), which is also its "reference" implementation.
