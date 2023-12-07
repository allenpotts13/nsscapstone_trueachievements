package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGameRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGameResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetGameLambda
        extends LambdaActivityRunner<GetGameRequest, GetGameResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetGameRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetGameRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetGameRequest unauthenticatedRequest = input.fromPath(path -> GetGameRequest.builder()
                    .withUniqueId(path.get("uniqueId"))
                    .build());
                return input.fromUserClaims(claims ->
                    GetGameRequest.builder()
                        .withUserId(claims.get("email"))
                        .withUniqueId(unauthenticatedRequest.getUniqueId())
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetGameActivity().handleRequest(request)
        );
    }
}
