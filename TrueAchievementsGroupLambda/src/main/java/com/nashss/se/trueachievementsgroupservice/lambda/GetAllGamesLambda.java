package com.nashss.se.trueachievementsgroupservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.trueachievementsgroupservice.activity.requests.GetAllGamesRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetAllGamesResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllGamesLambda
extends LambdaActivityRunner<GetAllGamesRequest, GetAllGamesResult>
implements RequestHandler<AuthenticatedLambdaRequest<GetAllGamesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllGamesRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetAllGamesRequest unauthenticatedRequest = input.fromPath(path -> GetAllGamesRequest.builder()
                    .build());
                return input.fromUserClaims(claims ->
                    GetAllGamesRequest.builder()
                        .withUserId(claims.get("email"))
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetAllGamesActivity().handleRequest(request)
        );
    }
}
