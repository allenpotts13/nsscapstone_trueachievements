package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.DeleteGameFromGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.DeleteGameFromGroupResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class DeleteGameFromGroupLambda
        extends LambdaActivityRunner<DeleteGameFromGroupRequest, DeleteGameFromGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteGameFromGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteGameFromGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                DeleteGameFromGroupRequest unauthenticatedRequest = input.fromBody(DeleteGameFromGroupRequest.class);
                String decodedGroupName = urlDecode(unauthenticatedRequest.getGroupName());
                return input.fromUserClaims(claims ->
                        DeleteGameFromGroupRequest.builder()
                                .withUserId(claims.get("email"))
                                .withGroupName(decodedGroupName)
                                .withUniqueId(unauthenticatedRequest.getUniqueId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteGameFromGroupActivity().handleRequest(request)
        );
    }

    private String urlDecode(String input) {
        try {
            return java.net.URLDecoder.decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("URL decoding error: " + e.getMessage(), e);
            return input;
        }
    }
}
