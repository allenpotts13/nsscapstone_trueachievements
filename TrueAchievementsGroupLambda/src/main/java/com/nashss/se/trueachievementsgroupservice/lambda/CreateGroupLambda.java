package com.nashss.se.trueachievementsgroupservice.lambda;

import com.nashss.se.trueachievementsgroupservice.activity.requests.CreateGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.CreateGroupResult;
import com.nashss.se.trueachievementsgroupservice.utils.UrlDecoderUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class CreateGroupLambda
        extends LambdaActivityRunner<CreateGroupRequest, CreateGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateGroupRequest unauthenticatedRequest = input.fromBody(CreateGroupRequest.class);

                String decodedGroupName = UrlDecoderUtils.urlDecode(unauthenticatedRequest.getGroupName());

                return input.fromUserClaims(claims ->
                    CreateGroupRequest.builder()
                        .withGroupName(decodedGroupName)
                        .withUserId(claims.get("email"))
                        .withGamesList(null)
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideCreateGroupActivity().handleRequest(request)
        );
    }

}
