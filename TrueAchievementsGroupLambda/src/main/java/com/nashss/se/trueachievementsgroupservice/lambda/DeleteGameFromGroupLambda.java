package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteGameFromGroupLambda
        extends LambdaActivityRunner<DeleteGameFromGroupRequest, DeleteGameFromGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteGameFromGroupRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteGameFromGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                DeleteGameFromGroupRequest unauthenticatedRequest = input.fromBody(DeleteGameFromGroupRequest.class);
                return input.fromUserClaims(claims ->
                        DeleteGameFromGroupRequest.builder()
                                .withUserId(unauthenticatedRequest.getUserId())
                                .withGroupName(unauthenticatedRequest.getGroupName())
                                .withUniqueId(unauthenticatedRequest.getUniqueId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteGameFromGroupActivity().handleRequest(request)
        );
    }
}
