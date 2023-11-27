package com.nashss.se.trueachievementsgroupservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.trueachievementsgroupservice.activity.requests.GetGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.GetGroupResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class GetGroupLambda
extends LambdaActivityRunner<GetGroupRequest, GetGroupResult>
implements RequestHandler<AuthenticatedLambdaRequest<GetGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetGroupRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetGroupRequest unauthenticatedRequest = input.fromPath(path -> GetGroupRequest.builder()
                    .withGroupName(urlDecode(path.get("groupName")))
                    .build());
                return input.fromUserClaims(claims ->
                    GetGroupRequest.builder()
                        .withUserId(claims.get("email"))
                        .withGroupName(unauthenticatedRequest.getGroupName())
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetGroupActivity().handleRequest(request)
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
