package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;
import com.nashss.se.trueachievementsgroupservice.utils.UrlDecoderUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteGameFromGroupLambda
        extends LambdaActivityRunner<DeleteGameFromGroupRequest, DeleteGameFromGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteGameFromGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteGameFromGroupRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                DeleteGameFromGroupRequest unauthenticatedRequest = input.fromPath(path ->
                    DeleteGameFromGroupRequest.builder()
                        .withGroupName(UrlDecoderUtils.urlDecode(path.get("groupName")))
                        .withUniqueId(path.get("uniqueId"))
                        .build());

                return input.fromUserClaims(claims ->
                        DeleteGameFromGroupRequest.builder()
                                .withUserId(claims.get("email"))
                                .withGroupName(unauthenticatedRequest.getGroupName())
                                .withUniqueId(unauthenticatedRequest.getUniqueId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteGameFromGroupActivity().handleRequest(request)
        );
    }
}
