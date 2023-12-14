package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetUserStatsRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetUserStatsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetUserStatsLambda
    extends LambdaActivityRunner<GetUserStatsRequest, GetUserStatsResult>
    implements RequestHandler<AuthenticatedLambdaRequest<GetUserStatsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetUserStatsRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                GetUserStatsRequest.builder()
                    .withUserId(claims.get("email"))
                    .build()),

            (request, serviceComponent) ->
                serviceComponent.provideGetUserStatsActivity().handleRequest(request)
        );
    }
}
