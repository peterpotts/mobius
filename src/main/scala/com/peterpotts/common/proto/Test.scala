package com.peterpotts.common.proto

/**
  *
syntax = "proto3";
package io.token.proto.gateway;

import "google/api/annotations.proto";

message CreateMemberRequest {
  string nonce = 1;
  string name = 2;
}

message CreateMemberResponse {
  string id = 1;
}

message GetMemberRequest {
  string id = 1;
  string another_param = 2;
}

message GetMemberResponse {
  string id = 1;
  string name = 2;
}


service Gateway {
  rpc CreateMember (CreateMemberRequest) returns (CreateMemberResponse) {
    option (google.api.http) = {
        post: "/members"
        body: "*"
    };
  }

  rpc GetMember (GetMemberRequest) returns (GetMemberResponse) {
    option (google.api.http) = {
        get: "/members/{id}"
    };
  }
}

  */
object Test {
  object message {
    def apply(name: Symbol)
  }


}

/**

case class CreateMemberRequest(nonce: String, name: String)
case class CreateMemberResponse(id: String)
case class GetMemberRequest(id: String, anotherParam: String)
case class GetMemberResponse(id: String, name: String)

class GatewayService(implicit val actorRefFactory: ActorRefFactory) extends HttpService {
  val route = pathPrefix("members") {
    pathEnd {
      entity(as[CreateMemberRequest]) { request =>
        complete {
          Created -> CreateMemberResponse(id = request.hashCode.toString)
        }
      }
    } ~
      path(Segment) { id =>
        parameter('anotherParam) { anotherParam =>
          complete {
            val request = GetMemberRequest(id, anotherParam)
            OK -> GetMemberResponse(id, name = request.anotherParam)
          }
        }
      }
  }
}

  */