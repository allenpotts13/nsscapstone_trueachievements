package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGamesInGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGamesInGroupResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.trueachievementsgroupservice.utils.UrlDecoderUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class GetGamesInGroupLambda
        extends LambdaActivityRunner<GetGamesInGroupRequest, GetGamesInGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetGamesInGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetGamesInGroupRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetGamesInGroupRequest unauthenticatedRequest =
                    input.fromPath(path -> GetGamesInGroupRequest.builder()
                    .withGroupName(UrlDecoderUtils.urlDecode(path.get("groupName")))
                    .build());
                return input.fromUserClaims(claims ->
                    GetGamesInGroupRequest.builder()
                        .withUserId(claims.get("email"))
                        .withGroupName(unauthenticatedRequest.getGroupName())
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetGamesInGroupActivity().handleRequest(request)
        );
    }
}
