package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGroupsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGroupsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllGroupsLambda
        extends LambdaActivityRunner<GetAllGroupsRequest, GetAllGroupsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllGroupsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllGroupsRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetAllGroupsRequest unauthenticatedRequest = input.fromPath(path -> GetAllGroupsRequest.builder()
                    .build());
                return input.fromUserClaims(claims ->
                    GetAllGroupsRequest.builder()
                        .withUserId(claims.get("email"))
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetAllGroupsActivity().handleRequest(request)
        );
    }
}
