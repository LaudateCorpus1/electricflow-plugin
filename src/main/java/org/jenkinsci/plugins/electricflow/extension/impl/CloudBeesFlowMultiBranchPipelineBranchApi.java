package org.jenkinsci.plugins.electricflow.extension.impl;

import hudson.model.ItemGroup;
import hudson.model.Run;
import jenkins.branch.Branch;
import jenkins.branch.BranchProjectFactory;
import jenkins.branch.MultiBranchProject;
import jenkins.scm.api.SCMHead;
import org.jenkinsci.plugins.electricflow.extension.CloudBeesFlowMultiBranchPipeline;
import org.jenkinsci.plugins.variant.OptionalExtension;

@OptionalExtension(requirePlugins = {"branch-api", "scm-api"})
public class CloudBeesFlowMultiBranchPipelineBranchApi extends CloudBeesFlowMultiBranchPipeline {
    public void populate(Run run) {
        ItemGroup parent = run.getParent().getParent();
        if (parent instanceof MultiBranchProject) {
            BranchProjectFactory projectFactory = ((MultiBranchProject) parent).getProjectFactory();
            if (projectFactory.isProject(run.getParent())) {
                Branch branch = projectFactory.getBranch(run.getParent());
                SCMHead head = branch.getHead();
                this.setBranchName(head.getName());
            }
        }
    }
}
